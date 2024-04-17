package bichla.league_project.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "league_entry")
public class LeagueEntry {

    @Id
    private String summonerId;

    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private Boolean hotStreak;
    private Boolean freshBlood;
    private Boolean inactive;
}
