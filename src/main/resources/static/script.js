const API_BASE_URL = 'http://localhost:8080/api';

async function searchPlayer() {
    const gameName = document.getElementById('gameName').value.trim();
    const tagLine = document.getElementById('tagLine').value.trim();
    const matchCount = document.getElementById('matchCount').value || 20;

    if (!gameName || !tagLine) {
        showError('Por favor, preencha o nome do jogador e a tag.');
        return;
    }

    showLoading(true);
    hideError();
    hideResults();

    try {
        const response = await fetch(
            `${API_BASE_URL}/player/stats?gameName=${encodeURIComponent(gameName)}&tagLine=${encodeURIComponent(tagLine)}&matchCount=${matchCount}`
        );

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.error || 'Erro ao buscar dados do jogador');
        }

        const data = await response.json();
        displayResults(data);
    } catch (error) {
        console.error('Error:', error);
        showError(`Erro: ${error.message}. Verifique se o nome e tag estão corretos e se você configurou a API key no application.properties.`);
    } finally {
        showLoading(false);
    }
}

function displayResults(data) {
    // Player Info
    const iconUrl = `https://ddragon.leagueoflegends.com/cdn/14.1.1/img/profileicon/${data.profileIconId}.png`;
    document.getElementById('playerIcon').src = iconUrl;
    document.getElementById('playerName').textContent = `${data.gameName}#${data.tagLine}`;
    document.getElementById('playerLevel').textContent = data.summonerLevel;

    // Overall Stats
    document.getElementById('totalGames').textContent = data.totalGames;
    document.getElementById('wins').textContent = data.wins;
    document.getElementById('losses').textContent = data.losses;
    document.getElementById('winRate').textContent = `${data.winRate.toFixed(1)}%`;

    // Champion Stats
    displayChampionStats(data.topChampions);

    // Friend Stats
    displayFriendStats(data.friends);

    // Match History
    displayMatchStats(data.recentMatches);

    // Show results
    document.getElementById('results').style.display = 'block';
}

function displayChampionStats(champions) {
    const tbody = document.getElementById('championTableBody');
    tbody.innerHTML = '';

    if (!champions || champions.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" style="text-align: center;">Nenhum dado de campeão disponível</td></tr>';
        return;
    }

    champions.forEach(champ => {
        const kda = ((champ.avgKills + champ.avgAssists) / Math.max(champ.avgDeaths, 1)).toFixed(2);
        const winRateClass = champ.winRate >= 50 ? 'win-rate-positive' : 'win-rate-negative';
        
        const row = `
            <tr>
                <td class="champion-name">${champ.championName}</td>
                <td>${champ.gamesPlayed}</td>
                <td style="color: #10b981;">${champ.wins}</td>
                <td style="color: #ef4444;">${champ.losses}</td>
                <td class="${winRateClass}">${champ.winRate.toFixed(1)}%</td>
                <td>${champ.avgKills.toFixed(1)} / ${champ.avgDeaths.toFixed(1)} / ${champ.avgAssists.toFixed(1)} (${kda})</td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

function displayFriendStats(friends) {
    const tbody = document.getElementById('friendTableBody');
    tbody.innerHTML = '';

    if (!friends || friends.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" style="text-align: center;">Nenhum amigo encontrado (nenhum jogador com 2+ partidas juntos)</td></tr>';
        return;
    }

    friends.forEach(friend => {
        const winRateClass = friend.winRateWithFriend >= 50 ? 'win-rate-positive' : 'win-rate-negative';
        
        const row = `
            <tr>
                <td class="champion-name">${friend.friendName}</td>
                <td>${friend.gamesPlayedTogether}</td>
                <td style="color: #10b981;">${friend.winsWithFriend}</td>
                <td style="color: #ef4444;">${friend.lossesWithFriend}</td>
                <td class="${winRateClass}">${friend.winRateWithFriend.toFixed(1)}%</td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

function displayMatchStats(matches) {
    const tbody = document.getElementById('matchTableBody');
    tbody.innerHTML = '';

    if (!matches || matches.length === 0) {
        tbody.innerHTML = '<tr><td colspan="9" style="text-align: center;">Nenhuma partida disponível</td></tr>';
        return;
    }

    matches.forEach(match => {
        const resultClass = match.win ? 'win-rate-positive' : 'win-rate-negative';
        const resultText = match.win ? 'VITÓRIA' : 'DERROTA';
        const resultBg = match.win ? 'background: #d1fae5;' : 'background: #fee2e2;';
        
        const minutes = Math.floor(match.gameDuration / 60);
        const seconds = match.gameDuration % 60;
        const duration = `${minutes}:${seconds.toString().padStart(2, '0')}`;
        
        const goldFormatted = (match.goldEarned / 1000).toFixed(1) + 'k';
        
        const row = `
            <tr>
                <td class="${resultClass}" style="${resultBg} font-weight: bold;">${resultText}</td>
                <td class="champion-name">${match.championName}</td>
                <td style="font-weight: bold; color: #667eea;">${match.kda.toFixed(2)}</td>
                <td>${match.kills} / ${match.deaths} / ${match.assists}</td>
                <td>${match.champLevel}</td>
                <td>${match.minionsKilled}</td>
                <td>${goldFormatted}</td>
                <td>${duration}</td>
                <td style="font-size: 0.85rem;">${match.gameMode}</td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

function showLoading(show) {
    document.getElementById('loading').style.display = show ? 'block' : 'none';
    document.getElementById('searchBtn').disabled = show;
}

function showError(message) {
    const errorDiv = document.getElementById('error');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
}

function hideError() {
    document.getElementById('error').style.display = 'none';
}

function hideResults() {
    document.getElementById('results').style.display = 'none';
}

// Allow Enter key to trigger search
document.getElementById('gameName').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') searchPlayer();
});

document.getElementById('tagLine').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') searchPlayer();
});

document.getElementById('matchCount').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') searchPlayer();
});
