package com.example.Stepway.Service.impl;

import com.example.Stepway.Domain.Certification;
import com.example.Stepway.Domain.Content;
import com.example.Stepway.Domain.Course;
import com.example.Stepway.Exception.ResourceNotFound;
import com.example.Stepway.Repository.ContentRepository;
import com.example.Stepway.Repository.CourseRepository;
import com.example.Stepway.Service.ContentService;
import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.ContentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<ContentDto> getALlContents() {
        List<Content> contents = contentRepository.findAll();

        return contents.stream().map(content -> modelMapper.map(content, ContentDto.class)).collect(Collectors.toList());
    }

    @Override
    public ContentDto getContentsById(Long id) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        Content content = optionalContent.orElseThrow(() -> new ResourceNotFound("Content with the Id not Found : "+ id));
        return modelMapper.map(content,ContentDto.class);
    }

    @Override
    public ContentDto createContent(ContentDto contentDto) {
        Content content = modelMapper.map(contentDto,Content.class);
        Content savedContent = contentRepository.save(content);

        return modelMapper.map(savedContent,ContentDto.class);
    }

    @Override
    public ContentDto updateContentById(Long id, ContentDto contentDto) {
        Content content = contentRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Content not Found with the id : "+id));


        Course course = courseRepository.findById(contentDto.getCourseId()).orElseThrow(()-> new ResourceNotFound("Course Not Found with the id "+ contentDto.getCourseId()));
        content.setTitle(contentDto.getTitle());
        content.setUrl(contentDto.getUrl());
        content.setCourseId(course);

        Content updatedContent = contentRepository.save(content);
        return modelMapper.map(updatedContent,ContentDto.class);
    }

    @Override
    public void deleteContentById(Long id) {

        if(!contentRepository.existsById(id)){
            throw new ResourceNotFound("Content not found with the id : "+id);
        }
        contentRepository.deleteById(id);

    }
}
