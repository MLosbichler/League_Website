package bichla.league_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "riot_account")
public class RiotAccount {

    // PUUID is unique globally. It is uneffected by account name changes or region transfers.
    @Id
    private String puuid;

    private String gameName;
    private String tagLine;

    @OneToOne
    @JoinColumn(name = "puuid")
    private Summoner summoner;
}
