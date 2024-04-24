import DiscordLogin from "./DiscordLogin";

export default function Header() {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "space-between",
        height: "35px",
      }}
    >
      <DiscordLogin />
    </div>
  );
}
