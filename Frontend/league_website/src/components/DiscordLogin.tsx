import React from "react";

export default function DiscordLogin() {
  const discordLink =
    "https://discord.com/oauth2/authorize?client_id=1225456729110745128&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fprofile&scope=identify";

  const handleClick = () => {
    window.location.href = discordLink;
  };

  return (
    <button
      onClick={handleClick}
      style={{
        backgroundColor: "#7289da",
        color: "white",
        border: "none",
        padding: "5px 15px",
        borderRadius: "5px",
        cursor: "pointer",
        fontSize: "16px",
        marginLeft: "auto",
        marginRight: "10px",
      }}
    >
      Login
    </button>
  );
}
