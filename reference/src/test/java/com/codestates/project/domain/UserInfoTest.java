package com.codestates.project.domain;

import com.codestates.project.exception.CustomException;
import com.codestates.project.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.codestates.project.domain.UserLoanStatus.RETURNED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserInfoTest {

    private static final UserFixture fixture = new UserFixture();

    @DisplayName("대출 여부에 따른 사용자 삭제 가능 여부 확인")
    @MethodSource("getUser")
    @ParameterizedTest
    void checkDeletableUserStatus(UserInfo userInfo, boolean expected) {
        // given & when
        boolean deletable = userInfo.isNotDeletable();

        // then
        assertThat(deletable).isEqualTo(expected);
    }

    private static Stream<Arguments> getUser() {
        return Stream.of(
                Arguments.of(fixture.getUserInfoWithLoanHistory(LocalDate.now()), true),
                Arguments.of(fixture.getUserInfoWithReturnHistory(LocalDate.now(), LocalDate.now()), false)
        );
    }

    @DisplayName("대출 반납")
    @Test
    void returnBook() {
        // given
        UserInfo userInfo = fixture.getUserInfoWithLoanHistory(LocalDate.now());

        // when
        userInfo.returnBook(1000L);

        // then
        assertThat(userInfo.getUserLoanHistories().get(0).getStatus()).isEqualTo(RETURNED);
    }

    @DisplayName("대출중인 책이 5권 이상인 경우 대출 불가")
    @Test
    void isOverLoanCount() {
        // given
        UserInfo userInfo = new UserInfo("name", 33, Gender.MALE, "01012341234", 1000L,
                List.of(
                        fixture.getUserLoanHistory(LocalDate.now()),
                        fixture.getUserLoanHistory(LocalDate.now()),
                        fixture.getUserLoanHistory(LocalDate.now()),
                        fixture.getUserLoanHistory(LocalDate.now()),
                        fixture.getUserLoanHistory(LocalDate.now())
                ));

        // when & then
        assertThatThrownBy(() -> {
            userInfo.loanBook(1002L);
        }).isInstanceOf(CustomException.class);
    }

    @DisplayName("연체중인 책이 있으면 대출 불가")
    @Test
    void isOverDue() {
        // given
        LocalDate loanDate = LocalDate.of(2022, 8, 31);
        UserInfo userInfo = fixture.getUserInfoWithLoanHistory(loanDate);

        // when & then
        assertThatThrownBy(() -> {
            userInfo.loanBook(1002L);
        }).isInstanceOf(CustomException.class);
    }
}