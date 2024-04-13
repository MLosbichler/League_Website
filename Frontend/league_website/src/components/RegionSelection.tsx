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
  { value: "EUW", label: "EUW" },
  { value: "NA", label: "NA" },
  { value: "KR", label: "KR" },
];

const RegionSelection: React.FC<RegionSelectionProps> = ({
  onRegionChange,
}) => {
  const [selectedOption, setSelectedOption] = useState<OptionType | null>(null);

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
      placeholder="Select an option"
    />
  );
};

export default RegionSelection;
