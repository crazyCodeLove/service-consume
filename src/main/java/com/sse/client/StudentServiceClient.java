package com.sse.client;

import com.sse.api.StudentServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author pczhao
 * @email
 * @date 2018-11-05 21:44
 */

@FeignClient(value = "service-provider")
public interface StudentServiceClient extends StudentServiceApi {
}
