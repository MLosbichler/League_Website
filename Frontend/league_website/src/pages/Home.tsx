import RegionSelection from "../components/RegionSelection";
import SearchBar from "../components/SummonerSearch";
import React, { useState } from "react";

interface SearchResult {
  id: number;
  title: string;
}

const Home: React.FC = () => {
  const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
  const [region, setRegion] = useState<String>();
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  const handleRegionChange = (selectedRegion: string) => {
    setRegion(selectedRegion);
  };

  const handleSearch = async (query: string) => {
    setLoading(true);
    setError("");

    try {
      const response = await fetch(
        `http://localhost:8080/api/rank/europe/${region}/${query}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch search results");
      }
      const data = await response.json();
      setSearchResults(data);
    } catch (error) {
      setError("Failed to fetch search results");
      console.error("Error fetching search results:", error);
    } finally {
      setLoading(false);
    }

    setSearchResults([{ id: 1, title: `Search result for "${query}"` }]);
  };

  return (
    <>
      {" "}
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
        <RegionSelection onRegionChange={handleRegionChange} />
      </div>
    </>
  );
};

export default Home;
