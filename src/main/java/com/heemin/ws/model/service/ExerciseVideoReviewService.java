package com.ssafy.ssafit.model.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.exception.ForbiddenException;
import com.ssafy.ssafit.model.dao.ExerciseVideoReviewDao;
import com.ssafy.ssafit.model.dto.review.ExerciseVideoReview;

@Service
public class ExerciseVideoReviewService {
    private ExerciseVideoReviewDao reviewDao;
    
    public ExerciseVideoReviewService(ExerciseVideoReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    
    public void add(ExerciseVideoReview review) {
        if (reviewDao.insert(review) != 1) {
            throw new ForbiddenException();
        }
    }
    
    public void update(ExerciseVideoReview review) {
    	review.setUpdateDate(LocalDate.now());
        if (reviewDao.update(review) != 1) {
            throw new ForbiddenException();
        }
    }
    
    public void remove(long id) {
        if (reviewDao.delete(id) != 1) {
            throw new ForbiddenException();
        }
    }
    
    public List<ExerciseVideoReview> getByVideoId(long id){
        return reviewDao.selectByVideoId(id);
    }
    
    public List<ExerciseVideoReview> getByMemberId(long id){
        return reviewDao.selectByMemberId(id);
    }
    
    public boolean setReviewLike(long memberId, long reviewId, boolean like) {
		if (like)
			return reviewDao.insertLike(memberId, reviewId) == 1;
		else
			return reviewDao.deleteLike(memberId, reviewId) == 1;
	}
	
	public boolean setReviewBlock(long memberId, long reviewId, boolean block) {
		if (block)
			return reviewDao.insertBlock(memberId, reviewId) == 1;
		else
			return reviewDao.deleteBlock(memberId, reviewId) == 1;
	}
}