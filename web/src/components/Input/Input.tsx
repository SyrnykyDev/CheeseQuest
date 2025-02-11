"use client";

// *** COMPONENTS ***
import React, { ChangeEvent, useEffect, useState } from "react";

// *** STYLES ***
import styles from "./Input.module.scss";

interface InputProps extends React.InputHTMLAttributes<any> {
  title?: string;
  inputType?: "sans" | "round";
  value?: string | number;
  required?: boolean;
}

/**
 * Extended default input
 **/
const Input = (props: InputProps) => {
  const { onChange, required, inputType = "sans", ...otherProps } = props;

  const [change, setChange] = useState(true);
  // const [state, setState] = useState("");

  // useEffect(() => {
  //   props.value !== undefined && setState(props.value);
  // }, [props.value]);

  const onMiddleChange = (event: React.FormEvent<HTMLInputElement>): void => {
    console.log(event?.target?.value);
    // if () {
    if (onChange) onChange(event);
    // setState(event?.target?.value);
    // } else {
    //   setState(event?.target?.value);
    // }
  };

  useEffect(() => {
    const changeStage = () => {
      setChange(true);
      setTimeout(() => {
        setChange(false);
      }, 150); // Adjust the timeout duration equal to your animation time, in your case is 1100
    };
    changeStage();
  }, [props.value]);

  return (
    <div className={`${styles.input} ${styles[`input_${inputType}`]}`}>
      {props?.title && <span>{props.title}</span>}

      <input
        className={`${styles.input_input} ${change ? styles.input_anim : ""}`}
        {...otherProps}
        placeholder={
          props?.placeholder && `${props?.placeholder} ${required ? "*" : ""}`
        }
        value={props.value || ""}
        onChange={onMiddleChange}
      />
    </div>
  );
};

export default Input;
