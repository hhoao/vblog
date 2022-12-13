import { BasicPageParams } from '/@/api/models/BaseModel';

export interface TagModel {
  id: number;
  label: string;
}
export interface TagModelParams {
  id?: number;
  label?: string;
}

export type TagPageModelParams = TagModelParams & BasicPageParams;
