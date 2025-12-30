package com.bag.foro_hub.service;

import com.bag.foro_hub.exceptions.ReplyNotFoundException;
import com.bag.foro_hub.exceptions.RoleAccessDeniedException;
import com.bag.foro_hub.exceptions.TopicNotFoundException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.mapper.ReplyMapper;
import com.bag.foro_hub.model.dto.request.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;
import com.bag.foro_hub.model.entity.Reply;
import com.bag.foro_hub.model.entity.Topic;
import com.bag.foro_hub.model.entity.User;
import com.bag.foro_hub.repository.ReplyRepository;
import com.bag.foro_hub.repository.TopicRepository;
import com.bag.foro_hub.repository.UserRepository;
import com.bag.foro_hub.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyRepository replyRepository;
  private final TopicRepository topicRepository;
  private final UserRepository userRepository;
  private final ReplyMapper replyMapper;

  @Override
  @Transactional
  public ReplyResponse save(CreateReplyRequest request, Long authId) {
    Topic topic =
        topicRepository.findById(request.topicId()).orElseThrow(TopicNotFoundException::new);

    User user = userRepository.findById(authId).orElseThrow(UserNotFoundException::new);

    Reply reply = new Reply();
    reply.setMessage(request.message());
    reply.setUser(user);
    reply.setTopic(topic);

    return replyMapper.toReplyResponse(replyRepository.save(reply));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReplyResponse> findAll() {
    return replyRepository.findAll().stream().map(replyMapper::toReplyResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public ReplyResponse findById(Long id) {
    return replyRepository
        .findById(id)
        .map(replyMapper::toReplyResponse)
        .orElseThrow(ReplyNotFoundException::new);
  }

  @Override
  @Transactional
  public void markAsSolution(Long replyId, Long authid) {
    Reply reply = replyRepository.findById(replyId).orElseThrow(ReplyNotFoundException::new);

    Topic topic = reply.getTopic();

    boolean isOwner = topic.belongsToUser(authid);

    if (!isOwner) {
      throw new RoleAccessDeniedException("You are not the owner of this topic");
    }

    replyRepository
        .findByTopicIdAndIsSolutionTrue(topic.getId())
        .ifPresent(
            currentSolution -> {
              currentSolution.setIsSolution(false);
              replyRepository.save(currentSolution);
            });

    reply.setIsSolution(true);
    topic.setStatus(Status.SOLVED);
    replyRepository.save(reply);
    topicRepository.save(topic);
  }
}
