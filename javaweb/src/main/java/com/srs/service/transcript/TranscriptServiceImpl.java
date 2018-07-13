package com.srs.service.transcript;

import com.srs.dao.SectionRepository;
import com.srs.dao.SysUserRepository;
import com.srs.dao.TranscriptRepository;
import com.srs.domain.TranscriptCatalog;
import com.srs.model.Section;
import com.srs.model.Student;
import com.srs.model.Transcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    @Autowired
    TranscriptRepository transcriptRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SectionRepository sectionRepository;

    private final TranscriptCatalog catalog = TranscriptCatalog.getInstance ( );

    @Override
    public String findAllByStudentJson ( Student student ) {

        return catalog.getTranscriptJsonForStudent ( transcriptRepository.getTranscriptsByStudentEquals ( student ) );
    }

    @Override
    public String findAllByStudentJsonGraded ( Student student ) {

        return catalog.getTranscriptJsonForStudentGraded ( transcriptRepository.getTranscriptsByStudentEquals ( student ) );
    }

    @Override
    public String chooseOneSection ( Student student , Section section ) {

        Transcript transcript = new Transcript ( section , student );
        String     s          = transcript.canChoose ( );
        if ( s == null ) {
            transcriptRepository.save ( transcript );
            return "{\"msg\":\"操作成功\"}";
        } else {
            return s;
        }
    }

    @Override
    public boolean unChooseOneSection ( Integer id ) {

        transcriptRepository.deleteById ( id );
        return true;
    }

    @Override
    public String getTranscriptsBySectionId ( Integer sectionId ) {

        Optional < Section > byId = sectionRepository.findById ( sectionId );
        Section              section;
        if ( byId.isPresent ( ) ) {
            section = byId.get ( );
            List < Transcript > transcripts = section.getTranscripts ( );
            if ( transcripts.size ( ) > section.getCapacity ( ) ) {
                transcripts = transcripts.subList ( 0 , section.getCapacity ( ) - 1 );
            }
            return catalog.getTranscriptJsonForTeacher ( transcripts );
        } else {
            return null;
        }
    }

    @Override
    public String updateTranscripts ( List < Transcript > transcripts ) {

        List < Transcript > saveEd = transcriptRepository.saveAll ( transcripts );
        return catalog.getTranscriptJsonForTeacher ( saveEd );
    }
}
