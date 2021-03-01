package com.wpg.projectversion.service.feign;

import com.wpg.auth.service.feign.vo.TenantVO;
import com.wpg.tools.common.module.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hufei
 * @Description: RemoteService
 * @date 2021/2/4 13:48
 */
@FeignClient(name = "wpg-project-version-manager-web",contextId = "remoteTenantService")
public interface RemoteTenantService {

    @GetMapping("/rest/tenant/get/{id}")
    RestResult<TenantVO> getById(@PathVariable("id") String id);

}
