package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;

import java.util.List;

public interface ReplyService {

  ReplyResponse save(CreateReplyRequest request, Long authId);

  List<ReplyResponse> findAll();

  ReplyResponse findById(Long id);

  void markAsSolution(Long replyId, Long authid);
}
