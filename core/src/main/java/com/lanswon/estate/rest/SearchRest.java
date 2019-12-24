package com.lanswon.estate.rest;

import com.lanswon.commons.web.dto.DTO;
import com.lanswon.commons.web.rtn.CustomRtnEnum;
import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.search.HouseName;
import com.lanswon.estate.bean.search.LandName;
import com.lanswon.estate.bean.search.ResourceName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/search/")
@Api(value = "搜索引擎",tags = "搜索引擎")
@Slf4j
public class SearchRest {

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;


	@GetMapping(value = "/land")
	@ApiOperation(value = "land")
	public DTO searchLand(@RequestParam(value = "landNo")String landNo){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.matchQuery("name", landNo));
		PageRequest page = PageRequest.of(0, 5000);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(builder)
				.withPageable(page)
				.build();
		List<LandName> landNames = elasticsearchTemplate.queryForList(nativeSearchQuery, LandName.class);

		return customRtn(landNames);
	}

	@GetMapping(value = "/house")
	@ApiOperation(value = "house")
	public DTO searchHouse(@RequestParam(value = "houseNO")String houseNO){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.matchPhrasePrefixQuery("name", houseNO));
		PageRequest page = PageRequest.of(0, 5000);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(builder)
				.withPageable(page)
				.build();
		List<HouseName> houseNames = elasticsearchTemplate.queryForList(nativeSearchQuery, HouseName.class);

		return customRtn(houseNames);
	}

	@GetMapping(value = "/resource")
	@ApiOperation(value = "resource")
	public DTO searchResource(@RequestParam(value = "resourceName")String resourceName){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.matchPhrasePrefixQuery("name", resourceName));
		PageRequest page = PageRequest.of(0, 5);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(builder)
				.withPageable(page)
				.build();
		List<ResourceName> resourceNames = elasticsearchTemplate.queryForList(nativeSearchQuery, ResourceName.class);

		return customRtn(resourceNames);
	}



	private DTO customRtn(List list){
		if (list.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),list);
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),list);
	}
}
