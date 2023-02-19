package ru.practicum.Compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;

    @Override
    public List<CompilationDto> getAllCompilations(boolean pinned, long from, long size) {
        return null;
    }

    @Override
    public CompilationDto getCompilationById(long compId) {
        return null;
    }

    @Override
    public CompilationDto saveCompilation(CompilationNewDto compilationNewDto) {
        return null;
    }

    @Override
    public void removeCompilation(long compId) {

    }

    @Override
    public CompilationDto updateCompilation(long compId, CompilationNewDto compilationNewDto) {
        return null;
    }
}