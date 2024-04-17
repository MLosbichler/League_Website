import RegionSelection from "../components/RegionSelection";
import SearchBar from "../components/SummonerSearch";
import React, { useState } from "react";

interface SearchResult {
  id: number;
  title: string;
}

const Home: React.FC = () => {
  const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
  const [server, setServer] = useState<String>();
  const [continent, setContinent] = useState<String>("EUROPE");
  const [loading, setLoading] = useState<boolean>(false);

  const handleServerChange = (selectedRegion: string) => {
    setServer(selectedRegion);

    if (server === "EUW") {
      setContinent("EUROPE");
    } else if (server === "NA") {
      setContinent("AMERICAS");
    } else if (server === "KR") {
      setContinent("ASIA");
    }
  };

  const handleSearch = async (query: string) => {
    if (!query.includes("#")) {
      console.error("Bitte gib eine gültige Riot-ID an.");
    } else {
      setLoading(true);
      let summonerName: String[] = query
        .split("#")
        .filter((substring) => !substring.includes("#"))
        .map((substring) => substring.toUpperCase());

      try {
        const response = await fetch(
          `http://localhost:8080/api/rank/${continent}/${server}/${summonerName[0]}/${summonerName[1]}`
        );
        const data = await response.json();
        setSearchResults(data);
      } catch (error) {
        console.error("Error fetching search results:", error);
      } finally {
        setLoading(false);
      }

      setSearchResults([{ id: 1, title: `Search result for "${query}"` }]);
    }
  };

  return (
    <>
      <div>
        <h1>Summoner:</h1>
        <SearchBar onSearch={handleSearch} />
        <ul>
          {searchResults.map((result) => (
            <li key={result.id}>{result.title}</li>
          ))}
        </ul>
      </div>
      <div>
        <h1>Server:</h1>
        <RegionSelection onRegionChange={handleServerChange} />
      </div>
    </>
  );
};

export default Home;
