package si.zpiz.sample.domain.misc;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paged {
    @Min(0)
    private int page;

    @Min(0)
    @Max(1000)
    @Schema(defaultValue = "20")
    private int size = 20;

    private String[] sort;

    private Direction[] sortDirection;

    @JsonIgnore
    public Pageable getPageable() {
        Sort sorter = Sort.unsorted();
        if (sort != null && sortDirection != null && sort.length != sortDirection.length) {
            throw new IllegalStateException("sort and sortDirection must be of same length");
        }

        if (sort != null && sortDirection != null) {
            for (int i = 0; i < sort.length; i++) {
                sorter = sorter.and(Sort.by(sortDirection[i], sort[i]));
            }
        }

        return org.springframework.data.domain.PageRequest.of(page, size, sorter);
    }
}
