package nz.clem.book.feedback;

import jakarta.validation.Valid;
import nz.clem.book.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedbackMapper {
    public Feedback toFeedback(@Valid FeedbackRequest request) {

        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                                .id(request.bookId())
                                .archived(false) // just to satisfy Lombok
                                .sharable(false)
                        .build())
                .build();

    }

    public FeedbackResponse toFeedbackResponse(Feedback feedback, Integer userId) {
        return FeedbackResponse.builder()
            .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), userId))
                .build();
    }
}
