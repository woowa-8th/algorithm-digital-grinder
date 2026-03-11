package service;

import static org.assertj.core.api.Assertions.assertThat;

import constant.HitOrStand;
import constant.Rank;
import constant.Suit;
import domain.BetAmount;
import domain.Card;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import dto.ParticipantResultDto;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    @Nested
    class CreatePlayersTest {

        @Nested
        class Success {

            @Test
            void 플레이어를_생성하면_플레이어_Dto_목록에서_확인할_수_있다() {

                // given
                BlackjackService blackjackService = new BlackjackService();

                // when
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                List<ParticipantDto> actual = blackjackService.getPlayerDtoList();

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("jacob");
                assertThat(actual.get(1).name()).isEqualTo("seoye");
            }
        }
    }

    @Nested
    class DealInitialCardsTest {

        @Nested
        class Success {

            @Test
            void 초기_카드를_분배하면_플레이어는_2장씩_가져야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));

                // when
                blackjackService.dealInitialCards();
                List<ParticipantDto> actual = blackjackService.getBlackjackResult();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.get(0).name()).isEqualTo("딜러");
                assertThat(actual.get(1).hand()).hasSize(2);
                assertThat(actual.get(2).hand()).hasSize(2);
            }
        }
    }

    @Nested
    class DrawDealerCardTest {

        @Nested
        class Success {

            @Test
            void 딜러_점수가_16_이하면_카드를_추가로_뽑아야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                boolean actual = blackjackService.drawDealerCard();

                // then
                assertThat(actual).isTrue();
                assertThat(blackjackService.getBlackjackResult().get(0).score()).isEqualTo(25);
            }

            @Test
            void 딜러_점수가_17_이상이면_카드를_추가로_뽑지_않아야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                boolean actual = blackjackService.drawDealerCard();

                // then
                assertThat(actual).isFalse();
                assertThat(blackjackService.getBlackjackResult().get(0).score()).isEqualTo(17);
            }
        }
    }

    @Nested
    class DrawCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_뽑으면_null이_아닌_카드를_반환해야_한다() {

                // given
                BlackjackService blackjackService = new BlackjackService();

                // when
                Card actual = blackjackService.drawCard();

                // then
                assertThat(actual).isNotNull();
            }

            @Test
            void 카드가_모두_소진되면_null을_반환해야_한다() {

                // given
                BlackjackService blackjackService = new BlackjackService();
                for (int i = 0; i < 312; i++) {
                    blackjackService.drawCard();
                }

                // when
                Card actual = blackjackService.drawCard();

                // then
                assertThat(actual).isNull();
            }
        }
    }

    @Nested
    class GetPlayerDtoListTest {

        @Nested
        class Success {

            @Test
            void 플레이어_Dto_목록만_반환해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                List<ParticipantDto> actual = blackjackService.getPlayerDtoList();

                // then
                assertThat(actual).hasSize(2);
                assertThat(actual.get(0).name()).isEqualTo("jacob");
                assertThat(actual.get(1).name()).isEqualTo("seoye");
            }
        }
    }

    @Nested
    class GetInitialParticipantDtoListTest {

        @Nested
        class Success {

            @Test
            void 초기_참가자_목록은_딜러_한_장과_플레이어_정보를_반환해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                List<ParticipantDto> actual = blackjackService.getInitialParticipantDtoList();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.get(0).name()).isEqualTo("딜러");
                assertThat(actual.get(0).hand()).containsExactly("10하트");
                assertThat(actual.get(1).hand()).hasSize(2);
                assertThat(actual.get(2).hand()).hasSize(2);
            }
        }
    }

    @Nested
    class GetBlackjackResultTest {

        @Nested
        class Success {

            @Test
            void 최종_결과는_딜러와_플레이어_정보를_반환해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                List<ParticipantDto> actual = blackjackService.getBlackjackResult();

                // then
                assertThat(actual).hasSize(3);
                assertThat(actual.get(0).name()).isEqualTo("딜러");
                assertThat(actual.get(0).hand()).hasSize(1);
                assertThat(actual.get(1).name()).isEqualTo("jacob");
                assertThat(actual.get(2).name()).isEqualTo("seoye");
            }
        }
    }

    @Nested
    class GetBlackjackStatisticsTest {

        @Nested
        class Success {

            @Test
            void 플레이어_결과와_딜러_수익을_반환해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.setBetAmount("jacob", new BetAmount("2000"));
                blackjackService.setBetAmount("seoye", new BetAmount("3000"));
                blackjackService.dealInitialCards();
                blackjackService.drawDealerCard();

                // when
                BlackjackStatisticsDto actual = blackjackService.getBlackjackStatistics();

                // then
                assertThat(actual.dealerProfit()).isEqualTo(-5000);
                assertThat(actual.participantResultDtoList()).containsExactly(
                        new ParticipantResultDto("jacob", 2000),
                        new ParticipantResultDto("seoye", 3000)
                );
            }
        }
    }

    @Nested
    class CalculatePlayerResultsTest {

        @Nested
        class Success {

            @Test
            void 딜러와_플레이어_점수로_승무패를_계산해야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SEVEN, Suit.SPADE),
                        card(Rank.TEN, Suit.CLOVER), card(Rank.NINE, Suit.DIAMOND),
                        card(Rank.TEN, Suit.DIAMOND), card(Rank.SEVEN, Suit.HEART),
                        card(Rank.EIGHT, Suit.SPADE), card(Rank.SEVEN, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye", "brown"));
                blackjackService.setBetAmount("jacob", new BetAmount("1000"));
                blackjackService.setBetAmount("seoye", new BetAmount("1000"));
                blackjackService.setBetAmount("brown", new BetAmount("1000"));
                blackjackService.dealInitialCards();

                // when
                List<ParticipantResultDto> actual = blackjackService.calculatePlayerResults();

                // then
                assertThat(actual).containsExactly(
                        new ParticipantResultDto("jacob", 1000),
                        new ParticipantResultDto("seoye", 0),
                        new ParticipantResultDto("brown", -1000)
                );
            }
        }
    }

    @Nested
    class UpdatePlayerTest {

        @Nested
        class Success {

            @Test
            void 플레이어를_업데이트하면_카드가_한_장_추가된_Dto를_반환한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.ACE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.dealInitialCards();

                // when
                ParticipantDto actual = blackjackService.updatePlayer("jacob");

                // then
                assertThat(actual.name()).isEqualTo("jacob");
                assertThat(actual.hand()).hasSize(3);
                assertThat(actual.hand()).contains("A클로버");
            }
        }
    }

    @Nested
    class IsHitTest {

        @Nested
        class Success {

            @Test
            void 히트_입력이면_true를_반환한다() {

                // given
                BlackjackService blackjackService = new BlackjackService();
                HitOrStand hitOrStand = HitOrStand.from("y");

                // when
                boolean actual = blackjackService.isHit(hitOrStand);

                // then
                assertThat(actual).isTrue();
            }
        }
    }

    @Nested
    class IsStandTest {

        @Nested
        class Success {

            @Test
            void 스탠드_입력이면_true를_반환한다() {

                // given
                BlackjackService blackjackService = new BlackjackService();
                HitOrStand hitOrStand = HitOrStand.from("n");

                // when
                boolean actual = blackjackService.isStand(hitOrStand);

                // then
                assertThat(actual).isTrue();
            }
        }
    }

    @Nested
    class SetBetAmountTest {

        @Nested
        class Success {

            @Test
            void 배팅금액을_설정하면_결과_계산에_반영되어야_한다() {

                // given
                BlackjackService blackjackService = new FixedDeckBlackjackService(List.of(
                        card(Rank.TEN, Suit.HEART), card(Rank.SIX, Suit.SPADE),
                        card(Rank.TWO, Suit.CLOVER), card(Rank.THREE, Suit.DIAMOND),
                        card(Rank.FOUR, Suit.HEART), card(Rank.FIVE, Suit.SPADE),
                        card(Rank.NINE, Suit.CLOVER)
                ));
                blackjackService.createPlayers(List.of("jacob", "seoye"));
                blackjackService.setBetAmount("jacob", new BetAmount("2000"));
                blackjackService.setBetAmount("seoye", new BetAmount("3000"));
                blackjackService.dealInitialCards();
                blackjackService.drawDealerCard();

                // when
                List<ParticipantResultDto> actual = blackjackService.calculatePlayerResults();

                // then
                assertThat(actual).containsExactly(
                        new ParticipantResultDto("jacob", 2000),
                        new ParticipantResultDto("seoye", 3000)
                );
            }
        }
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    private static class FixedDeckBlackjackService extends BlackjackService {

        private final Deque<Card> cards;

        private FixedDeckBlackjackService(List<Card> cards) {
            this.cards = new ArrayDeque<>(cards);
        }

        @Override
        public Card drawCard() {
            if (cards.isEmpty()) {
                return null;
            }
            return cards.removeFirst();
        }
    }
}
