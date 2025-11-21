package org.example.interviewmanager.utils;

import org.example.interviewmanager.dto.InterviewDTO;
import org.example.interviewmanager.repository.entity.Interview;

public class InterviewMapper {

    public static InterviewDTO toDTO(Interview interview) {
        return new InterviewDTO(
                interview.getId(),
                interview.getType(),
                interview.getInterviewDate(),
                OpenPositionMapper.toDTO(interview.getPosition())
        );
    }

    public Interview toEntity(InterviewDTO interviewDTO) {
        Interview interview = new Interview();
        interview.setInterviewDate(interviewDTO.interviewDate());
        interview.setId(interviewDTO.id());
        interview.setPosition(OpenPositionMapper.toEntity(interviewDTO.position()));
        interview.setType(interviewDTO.type());
        return interview;
    }
}
