import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

interface CounterState {
  value: number
}

const initialState: CounterState = { value: 0 }

const counterSlice = createSlice({
  name: 'counter',
  initialState,
  reducers: {
    increment: (state) => { state.value++ },
    decrement: (state) => { state.value-- },
    incrementAsync: (_state, _action: PayloadAction<number>) => {},
  },
})

export const { increment, decrement, incrementAsync } = counterSlice.actions
export default counterSlice.reducer
