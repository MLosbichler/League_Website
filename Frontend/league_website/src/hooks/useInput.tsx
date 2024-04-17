import { ChangeEvent, useState } from "react";

export function useInput(initialValue: string) {
  const [value, setValue] = useState(initialValue);

  const handleChange = (input: ChangeEvent<HTMLInputElement>) => {
    setValue(input.target.value);
  };

  return {
    value,
    onChange: handleChange,
  };
}
