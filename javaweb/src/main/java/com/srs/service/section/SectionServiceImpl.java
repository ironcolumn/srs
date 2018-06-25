package com.srs.service.section;

import com.srs.dao.SectionRepository;
import com.srs.dao.SysUserRepository;
import com.srs.domain.SectionCatalog;
import com.srs.po.section.Section;
import com.srs.po.teacher.Teacher;
import com.srs.po.user.SysUser;
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
        Teacher teacher = sysUser.getTeacher ( );
        return catalog.getSectionJson ( teacher.getSections ( ) );
    }

    @Override
    public String getTeacherSectionAvailable ( ) {

        return catalog.getSectionJson ( sectionRepository.findSectionsByTeacherNull ( ) );
    }

    @Override
    public String getStudentSectionAvailable ( ) {

        return catalog.getSectionJson ( sectionRepository.findSectionsByTeacherNotNull ( ) );
    }

    @Override
    public Section chooseOneSection ( Teacher teacher , Section section ) {

        if ( section.getCourse ( ) == null ) {
            Optional < Section > id = sectionRepository.findById ( section.getId ( ) );
            if ( id.isPresent ( ) ) {
                section = id.get ( );
            }
        }
        section.setTeacher ( teacher );
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
        section.setTeacher ( null );
        sectionRepository.save ( section );
        return true;
    }

}
