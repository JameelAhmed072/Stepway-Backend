package com.example.Stepway.Repository;

import com.example.Stepway.Domain.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard,Long> {


}
