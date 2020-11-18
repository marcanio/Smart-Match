package com.smartMatch.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackDBRepository feedbackDBRepository;

    public FeedbackDB saveFeedback(FeedbackDB f) {
        return feedbackDBRepository.save(f);
    }

    public FeedbackDB getFeedback(String id) {
        return feedbackDBRepository.findById(id).get();
    }

    public Stream<FeedbackDB> getAllFeedbacks() {
        return feedbackDBRepository.findAll().stream();
    }
}