"use client"


// *** COMPONENTS ***
import React from 'react';

// *** STYLES ***
import styles from './Input.module.scss'


interface InputProps extends React.HTMLAttributes<HTMLInputElement> {
    title?: string;
}


/**
 * Extended default input
 **/
const Input = (props:InputProps) => {

    const {onChange} = props;

    const onMiddleChange = (event:React.FormEvent<HTMLInputElement>):void=>{
        if(onChange) onChange(event);
    }
    return (
        <div className={styles.input}>
            {props?.title &&<span>{props.title}</span>}

            <input className={styles.input_input} {...props} onChange={onMiddleChange}/>
        </div>
    );
};

export default Input;