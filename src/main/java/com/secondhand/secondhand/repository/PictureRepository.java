package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureEntity,Long> {

    void deleteAllByPublicId(String publicId);
}
