package com.srs.service.section;

import com.srs.dao.SectionRepository;
import com.srs.dao.SysUserRepository;
import com.srs.domain.SectionCatalog;
import com.srs.po.Professor;
import com.srs.po.Section;
import com.srs.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    private final SectionCatalog catalog = SectionCatalog.getInstance ( );

    @Override
    public Section findById ( Integer id ) {

        Optional < Section > byId = sectionRepository.findById ( id );
        return byId.orElse ( null );
    }

    @Override
    public String findAll ( ) {

        List < Section > all = sectionRepository.findAll ( );
        return catalog.getCourseArrangeJson ( all );
    }

    @Override
    public Section saveOne ( Section section ) {

        if ( section.getSectionSn ( ) == null ) {
            section.setSectionSn ( UUID.randomUUID ( ).toString ( ).substring ( 0 , 8 ) );
        }
        return sectionRepository.save ( section );
    }

    @Override
    public boolean deleteOneById ( Integer id ) {

        sectionRepository.deleteById ( id );
        return true;
    }

    @Override
    public String findMySections ( SysUser sysUser ) {

        Optional < SysUser > user = sysUserRepository.findById ( sysUser.getId ( ) );
        if ( user.isPresent ( ) ) {
            sysUser = user.get ( );
        }
        Professor professor = sysUser.getProfessor ( );
        return catalog.getSectionJson ( professor.getSections ( ) );
    }

    @Override
    public String getTeacherSectionAvailable ( ) {

        return catalog.getSectionJson ( sectionRepository.findSectionsByProfessorNull ( ) );
    }

    @Override
    public String getStudentSectionAvailable ( ) {

        return catalog.getSectionJson ( sectionRepository.findSectionsByProfessorNotNull ( ) );
    }

    @Override
    public Section chooseOneSection ( Professor professor , Section section ) {

        if ( section.getCourse ( ) == null ) {
            Optional < Section > id = sectionRepository.findById ( section.getId ( ) );
            if ( id.isPresent ( ) ) {
                section = id.get ( );
            }
        }
        section.setProfessor ( professor );
        return sectionRepository.save ( section );
    }

    @Override
    public boolean unChooseOneSection ( Integer sectionID ) {

        Optional < Section > sectionOptional = sectionRepository.findById ( sectionID );
        Section              section         = null;
        if ( sectionOptional.isPresent ( ) ) {
            section = sectionOptional.get ( );
        } else {
            return false;
        }
        section.setProfessor ( null );
        sectionRepository.save ( section );
        return true;
    }

}
