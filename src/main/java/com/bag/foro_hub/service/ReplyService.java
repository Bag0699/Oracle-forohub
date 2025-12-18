package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;

import java.util.List;

public interface ReplyService {

  ReplyResponse save(CreateReplyRequest request);

  List<ReplyResponse> findAll();

  ReplyResponse findById(Long id);
}
