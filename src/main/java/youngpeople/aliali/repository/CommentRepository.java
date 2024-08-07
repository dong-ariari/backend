package youngpeople.aliali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import youngpeople.aliali.entity.club.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndActivatedTrue(Long commentId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByPostIdAndParentCommentIdIsNull(Long postId);
}