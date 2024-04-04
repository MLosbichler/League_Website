package bichla.league_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "summoner")
public class Summoner {

    // PUUID is unique globally. It is uneffected by account name changes or region transfers.
    @Id
    private String puuid;

    private String accountId;
    private Integer profileIconId;
    private Long revisionDate;
    private String name;
    private String id;
    private Long summonerLevel;

    @OneToOne(mappedBy = "summoner")
    private RiotAccount riotAccount;

    @OneToOne
    @JoinColumn(name = "summonerId", referencedColumnName = "id")
    private LeagueEntry leagueEntry;
}
