package com.thacbao.social.usersevice.mapper;

import com.thacbao.social.usersevice.dto.request.UserRequest;
import com.thacbao.social.usersevice.dto.response.UserResponse;
import com.thacbao.social.usersevice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

}

