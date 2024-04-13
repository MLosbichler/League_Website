import SearchBar from "../components/SummonerSearch";
import React, { useState } from "react";

interface SearchResult {
  id: number;
  title: string;
}

const Home: React.FC = () => {
  const [searchResults, setSearchResults] = useState<SearchResult[]>([]);

  const handleSearch = (query: string) => {
    // Hier implementierst du deine Suchlogik, z. B. eine API-Anfrage,
    // um Suchergebnisse zu erhalten. Für dieses Beispiel fügen wir nur ein Dummy-Ergebnis hinzu.
    setSearchResults([{ id: 1, title: `Search result for "${query}"` }]);
  };

  return (
    <div>
      <h1>Search Page</h1>
      <SearchBar onSearch={handleSearch} />
      <ul>
        {searchResults.map((result) => (
          <li key={result.id}>{result.title}</li>
        ))}
      </ul>
    </div>
  );
};

export default Home;
