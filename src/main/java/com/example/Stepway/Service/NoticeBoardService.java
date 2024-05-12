package com.example.Stepway.Service;

import com.example.Stepway.dto.NoticeBoardDto;
import com.example.Stepway.dto.PaymentDto;

import java.util.List;

public interface NoticeBoardService {

    List<NoticeBoardDto> getALlNotices();

    public NoticeBoardDto getNoticeById(Long id);

    public NoticeBoardDto createNotice(NoticeBoardDto noticeBoardDto);
    NoticeBoardDto updateNoticeById(Long id,NoticeBoardDto noticeBoardDto);
    public void deleteNoticeById(Long id);
}
