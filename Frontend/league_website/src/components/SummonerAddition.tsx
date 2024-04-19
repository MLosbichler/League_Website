import { FormEvent, useState } from "react";
import RegionSelection from "./RegionSelection";
import { useInput } from "../hooks/useInput";

export function SummonerAddition() {
  const { value, onChange } = useInput("");
  const [server, setServer] = useState<String>("EUW1");
  const [continent, setContinent] = useState<String>("EUROPE");
  const [data, setData] = useState<JSON>();

  const handleSubmit = async (input: FormEvent<HTMLFormElement>) => {
    input.preventDefault();

    let summonerName: String[] = value
      .split("#")
      .filter((substring) => !substring.includes("#"))
      .map((substring) => substring.toUpperCase());

    try {
      const response = await fetch(
        `http://localhost:8080/api/rank/${continent}/${server}/${summonerName[0]}/${summonerName[1]}`
      );
      setData(await response.json());
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  };

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

  return (
    <div
      style={{
        width: "100%",
        height: "900px",
        backgroundColor: "pink",
      }}
    >
      <form onSubmit={handleSubmit}>
        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
            alignContent: "center",
          }}
        >
          <input
            type="text"
            placeholder="Summoner#TAG"
            value={value}
            onChange={onChange}
            style={{ textAlign: "center", marginTop: "40vh", width: "50vh" }}
          />
          <div style={{ marginTop: "40vh" }}>
            <RegionSelection onRegionChange={handleServerChange} />
          </div>
          <button type="submit" style={{ marginTop: "40vh" }}>
            Add
          </button>
        </div>
      </form>
      <div>{JSON.stringify(data, null, 2)}</div>
    </div>
  );
}
