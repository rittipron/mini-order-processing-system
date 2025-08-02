import { put, takeEvery, delay } from 'redux-saga/effects'
import { increment, incrementAsync } from '../slices/counterSlice'

function* handleIncrementAsync() {
  yield delay(1000)
  yield put(increment())
}

export default function* counterSaga() {
  yield takeEvery(incrementAsync.type, handleIncrementAsync)
}
