package com.bag.foro_hub.mapper;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.model.entity.Topic;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class, CourseMapper.class})
public interface TopicMapper {

  Topic toTopic(CreateTopicRequest request);

  TopicResponse toTopicResponse(Topic topic);
}
