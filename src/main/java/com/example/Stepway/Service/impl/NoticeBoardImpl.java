package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.NoticeBoard;
import com.example.Stepway.Domain.Payment;
import com.example.Stepway.Repository.NoticeBoardRepository;
import com.example.Stepway.Service.NoticeBoardService;
import com.example.Stepway.dto.NoticeBoardDto;
import com.example.Stepway.dto.PaymentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeBoardImpl implements NoticeBoardService {
    @Autowired
    NoticeBoardRepository noticeBoardRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<NoticeBoardDto> getALlNotices() {
        List<NoticeBoard> noticeBoards = noticeBoardRepository.findAll();
        return noticeBoards.stream().map(notice -> modelMapper.map(notice, NoticeBoardDto.class)).collect(Collectors.toList());
    }
    @Override
    public NoticeBoardDto getNoticeById(Long id) {
        return null;
    }

    @Override
    public NoticeBoardDto createNotice(NoticeBoardDto noticeBoardDto) {
        NoticeBoard noticeBoard = modelMapper.map(noticeBoardDto,NoticeBoard.class);
        NoticeBoard savedNotice = noticeBoardRepository.save(noticeBoard);
        return modelMapper.map(savedNotice,NoticeBoardDto.class);
    }
    @Override
    public NoticeBoardDto updateNoticeById(Long id, NoticeBoardDto noticeBoardDto) {
        return null;
    }
    @Override
    public void deleteNoticeById(Long id) {

    }
}
