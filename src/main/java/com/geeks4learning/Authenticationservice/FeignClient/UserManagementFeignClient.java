package com.geeks4learning.Authenticationservice.FeignClient;

import com.geeks4learning.Authenticationservice.Model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-management-service", path="/api/v1/user")
public interface UserManagementFeignClient {
    @GetMapping("/{email}")
    public UserModel getUserByEmail(@PathVariable String email);

}
