package com.codestates.project.fixture;

import com.codestates.project.domain.Gender;
import com.codestates.project.domain.UserInfo;
import com.codestates.project.domain.UserLoanHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codestates.project.domain.UserLoanStatus.LOANED;
import static com.codestates.project.domain.UserLoanStatus.RETURNED;

public class UserFixture {

    public UserInfo getUserInfo() {
        return new UserInfo("name", 33, Gender.MALE, "01012341234", 1000L);
    }

    public UserLoanHistory getUserReturnHistory(LocalDate loanDate, LocalDate returnedDate) {
        return new UserLoanHistory(RETURNED, loanDate, returnedDate, 1000L, 1000L, 1000L);
    }

    public UserLoanHistory getUserLoanHistory(LocalDate loanDate) {
        return new UserLoanHistory(LOANED, loanDate, null, 1000L, 1000L, 1000L);
    }

    public UserInfo getUserInfoWithLoanHistory(LocalDate loanDate) {
        return new UserInfo("name", 33, Gender.MALE, "01012341234", 1000L,
                new ArrayList<>(Collections.singletonList(getUserLoanHistory(loanDate)))
        );
    }

    public UserInfo getUserInfoWithReturnHistory(LocalDate loanDate, LocalDate returnedDate) {
        return new UserInfo("name", 33, Gender.MALE, "01012341234", 1000L,
                List.of(getUserReturnHistory(loanDate, returnedDate))
        );
    }
}