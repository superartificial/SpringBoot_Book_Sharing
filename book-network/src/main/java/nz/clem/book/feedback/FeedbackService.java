package nz.clem.book.feedback;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nz.clem.book.book.Book;
import nz.clem.book.book.BookResponse;
import nz.clem.book.book.BookRespository;
import nz.clem.book.common.PageResponse;
import nz.clem.book.exception.OperationNotPermittedException;
import nz.clem.book.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRespository bookRespository;
    private final FeedbackRespository feedbackRespository;
    private final FeedbackMapper feedbackMapper;

    public Integer save(@Valid FeedbackRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Book book = bookRespository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + request.bookId()));
        if(book.isArchived() || !book.isSharable()) {
            throw new OperationNotPermittedException("You cannot give feedback for an archived or non-sharable book.");
        }
        if(Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback for your own book");
        }
        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRespository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        Page<Feedback> feedbacks = feedbackRespository.findAllFeedbacksByBook(pageable, user.getId());
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map( f-> feedbackMapper.toFeedbackResponse(f,user.getId()))
                .toList();
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast());
    }
}
