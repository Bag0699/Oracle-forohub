package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.response.ReplyResponse;
import com.bag.foro_hub.model.entity.Reply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

  ReplyResponse toReplyResponse(Reply reply);
}
