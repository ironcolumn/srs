package com.srs.dao;

import com.srs.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {

    List<Section> findSectionsByProfessorNull ( );

    List<Section> findSectionsByProfessorNotNull ( );
}
