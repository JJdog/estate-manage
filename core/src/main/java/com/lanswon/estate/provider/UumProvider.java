package com.lanswon.estate.provider;


import com.lanswon.commons.web.rtn.DataRtnDTO;
import com.lanswon.estate.bean.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UUM调用
 *
 * @author jaswine
 */
@FeignClient(value = "CLOUD-UUM",fallback = UumFallback.class)
public interface UumProvider {


	@GetMapping(value = "/uum/user/simple/{uid}")
	DataRtnDTO<SimpleUserDTO> getSimpleUserInfoByUid(@PathVariable(value = "uid")Long uid);

}
