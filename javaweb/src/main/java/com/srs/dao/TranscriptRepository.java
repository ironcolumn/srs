package com.srs.dao;

import com.srs.po.Student;
import com.srs.po.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

    List<Transcript> getTranscriptsByStudentEquals ( Student student );
}
