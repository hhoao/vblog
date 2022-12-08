export interface BasicPageParams {
  page: number;
  pageSize: number;
}

export interface BasicFetchResult<T> {
  list: T[];
  total: number;
}

export interface CommonResult<T> {
  code: number;
  result: T;
  message: string;
  type?: string;
}
