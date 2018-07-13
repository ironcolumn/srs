package com.srs.service.transcript;

import com.srs.model.Section;
import com.srs.model.Student;
import com.srs.model.Transcript;

import java.util.List;

public interface TranscriptService {

    String findAllByStudentJson ( Student student );
    String findAllByStudentJsonGraded ( Student student );

    /**
     * 选一门课
     *
     * @param student 选课学生
     * @param section 要选的section
     *
     * @return 操作结果json格式形如@example:`{'msg':'操作成功'}`
     */
    String chooseOneSection ( Student student , Section section );

    /**
     * 退选一门课
     *
     * @param id 要退选的课程对应的transcript.Id
     *
     * @return 操作结果
     */
    boolean unChooseOneSection ( Integer id );

    /**
     * 根据sectionId获取所有transcript记录，忽略waitingList中的数据
     *
     * @param sectionId sectionId
     *
     * @return json格式数据
     */
    String getTranscriptsBySectionId ( Integer sectionId );

    String updateTranscripts ( List < Transcript > transcripts );

}
