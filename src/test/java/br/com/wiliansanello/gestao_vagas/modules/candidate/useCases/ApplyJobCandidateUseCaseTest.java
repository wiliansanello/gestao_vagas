package br.com.wiliansanello.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.com.wiliansanello.gestao_vagas.exceptions.JobNotFoundException;
import br.com.wiliansanello.gestao_vagas.exceptions.UserNotFoundException;
import br.com.wiliansanello.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.wiliansanello.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.wiliansanello.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.wiliansanello.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.wiliansanello.gestao_vagas.modules.company.entities.JobEntity;
import br.com.wiliansanello.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }        
    }
    
    @Test
    public void shouldNotBeAbleToApplyJobWithJobNotFound() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate,null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void shouldBeAbleToCreateANewApplyJob() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
            .jobId(idJob).build();

            var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

            when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
            when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

            when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);
           
            var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

            assertThat(result).hasFieldOrProperty("id");
            assertNotNull(result.getId());
    }
}
