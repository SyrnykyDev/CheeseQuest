"use client";

// *** COMPONENTS ***
import React, { ChangeEvent, useEffect, useState } from "react";

// *** STYLES ***
import styles from "./Input.module.scss";

interface InputProps extends React.InputHTMLAttributes<any> {
  title?: string;
  inputType?: "sans";
  value?: string | number;
  required?: boolean;
}

/**
 * Extended default input
 **/
const Input = (props: InputProps) => {
  const { onChange, required } = props;

  const [change, setChange] = useState(true);
  const [state, setState] = useState("");

  const onMiddleChange = (event: React.FormEvent<HTMLInputElement>): void => {
    console.log(event?.target?.value);
    if (onChange) {
      onChange(event);
    } else {
      setState(event?.target?.value);
    }
  };

  useEffect(() => {
    const changeStage = () => {
      setChange(true);
      setTimeout(() => {
        setChange(false);
      }, 150); // Adjust the timeout duration equal to your animation time, in your case is 1100
    };
    changeStage();
  }, [state]);

  return (
    <div className={`${styles.input} ${styles.input_sans}`}>
      {props?.title && <span>{props.title}</span>}

      <input
        className={`${styles.input_input} ${change ? styles.input_anim : ""}`}
        {...props}
        placeholder={
          props?.placeholder && `${props?.placeholder} ${required && "*"}`
        }
        value={state}
        onChange={onMiddleChange}
      />
    </div>
  );
};

export default Input;
