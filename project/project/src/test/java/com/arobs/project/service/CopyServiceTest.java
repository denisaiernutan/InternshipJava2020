package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;
import com.arobs.project.repository.CopyRepository;
import com.arobs.project.service.impl.CopyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CopyServiceTest {

    @InjectMocks
    private CopyServiceImpl copyService;

    @Mock
    private CopyRepository copyRepository;


    @Test
    void whenDeleteCopy_givenCopyId_returnBoolean() {
        Copy copy = new Copy(2, true, "available", new Book());
        when(copyRepository.findById(any(int.class))).thenReturn(copy);
        when(copyRepository.deleteCopy(any(Copy.class))).thenReturn(true);
        assertTrue(copyService.deleteCopy(2));
    }

}
