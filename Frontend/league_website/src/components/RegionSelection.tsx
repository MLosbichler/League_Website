import React, { useState } from "react";
import Select from "react-select";

interface RegionSelectionProps {
  onRegionChange: (region: string) => void;
}

type OptionType = {
  value: string;
  label: string;
};

const options: OptionType[] = [
  { value: "EUW1", label: "EUW" },
  { value: "NA1", label: "NA" },
  { value: "KR", label: "KR" },
];

const RegionSelection: React.FC<RegionSelectionProps> = ({
  onRegionChange,
}) => {
  const [selectedOption, setSelectedOption] = useState<OptionType | null>(
    options[0]
  );

  const handleChange = (selectedOption: OptionType | null) => {
    setSelectedOption(selectedOption);

    if (selectedOption) {
      onRegionChange(selectedOption.value);
    }
  };

  return (
    <Select
      options={options}
      value={selectedOption}
      onChange={handleChange}
      placeholder="EUW"
    />
  );
};

export default RegionSelection;
