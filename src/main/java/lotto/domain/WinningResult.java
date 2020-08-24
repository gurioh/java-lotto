package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.utils.CommonConstant.NUMBER_ONE;
import static lotto.utils.CommonConstant.NUMBER_ZERO;

public class WinningResult {

    private Map<Rank, Integer> winningResult;

    public WinningResult() {
        this.winningResult = new HashMap<>();
        this.winningResult = initMap(winningResult);
    }

    private Map<Rank, Integer> initMap(Map<Rank, Integer> winningResult) {
        for (Rank lotto : Rank.values()) {
            winningResult.put(lotto, NUMBER_ZERO);
        }
        return winningResult;
    }

    public Map<Rank, Integer> putRank(Rank rank) {
        if (winningResult.get(rank) != null) {
            winningResult.put(rank, winningResult.get(rank) + NUMBER_ONE);
        }
        return winningResult;
    }

    public WinningResult matchWinningNumber(List<Integer> winningNumbers, LottoStore lottoStore) {
        WinningResult winningResult = new WinningResult();
        for (int i = NUMBER_ZERO; i < lottoStore.size(); i++) {
            LottoTicket lottoTicket = lottoStore.getLottoTicket(i);
            int matchCount = lottoTicket.getMatchCount(winningNumbers);
            Rank rank = Rank.valudOf(matchCount);
            winningResult.putRank(rank);
        }
        return winningResult;
    }

    public Integer getWinningResult(Rank rank) {
        return winningResult.get(rank);
    }

}
