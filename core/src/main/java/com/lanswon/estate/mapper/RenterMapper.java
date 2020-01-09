package com.lanswon.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.estate.bean.pojo.Renter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RenterMapper extends BaseMapper<Renter> {

	@Select("SELECT concat(t.name,'$',t.id) FROM renter t")
	List<String> getAllRenterNameAndId();

}
